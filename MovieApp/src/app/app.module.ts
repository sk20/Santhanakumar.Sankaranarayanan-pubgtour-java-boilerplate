import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FavouriteComponent } from './components/favourite/favourite.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { SearchComponent } from './components/search/search.component';
import { TemplateComponent } from './components/template/template.component';
import { HeaderComponent } from './components/template/header/header.component';
import { UserComponent } from './components/user/user.component';
import { MovieComponent } from './components/movie/movie.component';
import { MatButtonModule } from  '@angular/material/button';
import { MatSidenavModule, } from  '@angular/material/sidenav';
import { MatIconModule, } from  '@angular/material/icon';
import { MatToolbarModule} from  '@angular/material/toolbar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from  '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AuthenticationService } from './service/authentication.service';
import { CanactivateGuard } from './components/guard/canactivate.guard';
import { FilterPipe } from './filter.pipe';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    FavouriteComponent,
    LoginComponent,
    RegisterComponent,
    SearchComponent,
    TemplateComponent,
    HeaderComponent,
    UserComponent,
    MovieComponent,
    FilterPipe
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    MatToolbarModule,
    MatSidenavModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,HttpClientModule
  ],
  providers: [AuthenticationService, CanactivateGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
