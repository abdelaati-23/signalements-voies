import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
export interface Signalement {
  id: string;
  description: string;
  statut: string;
  urgent: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class SignalementService {
  private readonly http=inject(HttpClient);
  lister() : Observable<Signalement[]>{
    return this.http.get<Signalement[]>('/api/signalements');
  }

}
