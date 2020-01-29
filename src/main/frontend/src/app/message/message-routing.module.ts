import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {MessageComponent} from "./message.component";


const routes: Routes = [
  {
    path: "message/:token",
    component: MessageComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MessageRoutingModule { }
