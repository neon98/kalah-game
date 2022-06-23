import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StartGameRequestDto } from 'src/app/core/models/dto/startGameRequestDto';
import { GameService } from 'src/app/core/services/game.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  player1: string = '';
  player2: string = '';
  
  constructor(private gameService: GameService, private router: Router) { }

  ngOnInit(): void {
  }

  startGame(){
    const request: StartGameRequestDto = {
      player1Name : this.player1.trim(),
      player2Name : this.player2.trim()
    }; 
    this.gameService.startGame(request);
    // navigate to game board only if game is created
    this.gameService.game$.subscribe(game => {
      if(game) {
        this.router.navigate(['/game']);
      }
    })
  }
}
