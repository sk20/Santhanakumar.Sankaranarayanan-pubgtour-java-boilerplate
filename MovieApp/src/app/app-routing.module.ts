import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TemplateComponent } from './components/template/template.component';
import { MovieComponent } from './components/movie/movie.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FavouriteComponent } from './components/favourite/favourite.component';
import { SearchComponent } from './components/search/search.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';


const routes: Routes = [
  { path: '',
     component: TemplateComponent, /*canActivate: [CanactivateGuard],*/
     children: [
      {
        path: '',
        component: MovieComponent,
        data: {
          title: ''
        }
      },
      {
        path: 'dashboard',
        component: DashboardComponent
       },
      { path: 'movie',
       component: MovieComponent,
       pathMatch: 'full'
       },
       { path: 'favourite',
       component: FavouriteComponent,
       pathMatch: 'full'
       },
       { path: 'search',
       component: SearchComponent,
       pathMatch: 'full'
       }
    ]
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
   { path: '**', component: TemplateComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
