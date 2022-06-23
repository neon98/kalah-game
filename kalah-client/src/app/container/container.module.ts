import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GameComponent } from './game/game.component';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { CoreModule } from '../core/core.module';



@NgModule({
  declarations: [
    GameComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    CoreModule,
    FormsModule
  ],
  exports:[
    GameComponent,
    HomeComponent
  ]
})
export class ContainerModule { }
