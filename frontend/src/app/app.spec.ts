import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { App } from './app';

describe('App', () => {
  let http: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [App],
      providers: [provideHttpClient(), provideHttpClientTesting()],
    }).compileComponents();
    http = TestBed.inject(HttpTestingController);
  });

  afterEach(() => http.verify());

  it('affiche les signalements renvoyés par l’API', async () => {
    const fixture = TestBed.createComponent(App);
    await fixture.whenStable();
    http.expectOne('/api/signalements').flush([
      { id: '1', description: 'Rail fissuré', statut: 'NOUVEAU', urgent: true },
    ]);
    await fixture.whenStable();

    const texte = (fixture.nativeElement as HTMLElement).textContent;
    expect(texte).toContain('Signalements voies');
    expect(texte).toContain('Rail fissuré');
    expect(texte).toContain('URGENT');
  });

  it('affiche une erreur si l’API échoue', async () => {
    const fixture = TestBed.createComponent(App);
    await fixture.whenStable();
    http.expectOne('/api/signalements').flush('boom', { status: 500, statusText: 'Server Error' });
    await fixture.whenStable();

    expect((fixture.nativeElement as HTMLElement).querySelector('[role=alert]')).not.toBeNull();
  });
});
