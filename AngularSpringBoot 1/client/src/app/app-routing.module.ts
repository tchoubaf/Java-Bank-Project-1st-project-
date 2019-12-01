import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from 'src/app/component/login/login.component';
import {RegisterComponent} from 'src/app/component/register/register.component';
import {ProfileComponent} from 'src/app/component/profile/profile.component';
import {UserListComponent} from 'src/app/component/user-list/user-list.component';
import {UserComponent} from 'src/app/component/user/user.component';

const routes: Routes = [
  {path:'', redirectTo:'login', pathMatch:'full'},
  
  {path:'login', component:LoginComponent},
  {path:'register', component:RegisterComponent},
  {path:'profile', component:ProfileComponent},
  {path:'user-list', component:UserListComponent},
  {path:'user', component:UserComponent},
  {path:'user/:id', component:UserComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }