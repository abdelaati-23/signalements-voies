import { Component, inject, signal } from '@angular/core';
import { Signalement, SignalementService } from './signalement/signalement.service';

@Component({
  selector: 'app-root',
  styleUrl: './app.scss',
  templateUrl: './app.html',
})
export class App {
  private readonly service=inject(SignalementService);

  protected readonly signalements=signal<Signalement[]>([]);
  protected readonly chargement= signal(true);
  protected readonly erreur=signal(false);
  constructor(){
    this.service.lister().subscribe({
      next: (signalements)=>{
        this.signalements.set(signalements);
        this.chargement.set(false);},
      error: ()=>{
        this.erreur.set(true);
        this.chargement.set(false);},
    });
  }


}
