import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { MainPageComponent } from './main-page/main-page.component';
import { CanActivateGuard } from './service/can-activate.guard';
import { MainPageAfterLoginComponent } from './main-page-after-login/main-page-after-login.component';
import { CanDeactivateGuard } from './can-deactivate.guard';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: MainPageComponent },
  {
    path: 'userHasLoggedIn',
    component: MainPageAfterLoginComponent,
    canActivate: [CanActivateGuard],
    canDeactivate: [CanDeactivateGuard],
  },
  { path: 'signUp', component: RegistrationFormComponent },
  { path: 'login', component: LoginPageComponent },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
//,
//
