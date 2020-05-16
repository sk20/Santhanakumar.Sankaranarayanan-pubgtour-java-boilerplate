import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouterService {

  constructor(private router: Router,
    private location: Location) { }

    routeToDashboard() {
      this.router.navigate(['']);
    }
  
    routeToLogin() {
      this.router.navigate(['login']);
    }
  
    routeToEditNoteView(noteId) {
      this.router.navigate([
        'dashboard',
        {
          outlets: {
            noteEditOutlet: [
              'note', noteId, 'edit'
            ]
          }
        }
      ]);
    }
    routeBack() {
      ///this.location.back();
    }
  
    routeToNoteView() {
      this.router.navigate(['dashboard/view/noteview']);
    }
  
    routeToListView() {
      this.router.navigate(['dashboard/view/listview']);
    }
}
