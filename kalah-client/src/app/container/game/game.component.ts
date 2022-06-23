import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Game, GameStatus, PlayGameRequestDto} from 'src/app/core/models';
import { GameService } from 'src/app/core/services/game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {

  game: Game = {} as Game;

  GameStatus = GameStatus;
  player1: number = 1;
  player2: number = 2;
  player1Pits: number[] = [0, 1, 2, 3, 4, 5 ];
  player2Pits: number[] = [12, 11, 10, 9, 8, 7];
  player1Kalaha: number= 6;
  player2Kalaha: number = 13;
  tiedGame: number = 3;

  constructor(private gameService: GameService, private router: Router) { }

  ngOnInit(): void {
    this.gameService.game$.subscribe(game => {
      this.game = game;
    })
  }

  play(pitNumber: number): void {
    const req = {
      gameId: this.game.id,
      pitNumber: pitNumber
    } as PlayGameRequestDto;
    this.gameService.playGame(req);
  }

  reset(): void{
    this.gameService.resetGame(this.game.id);
  }

  isGameInProgress(): boolean {
    return this.game && (this.game.gameStatus === GameStatus.IN_PROGRESS);
  }

  getCurrentTurnPlayerName(): string {
    if(!!this.game && this.game.currentTurn) {
      switch(this.game.currentTurn) {
        case this.player1:
          return `${this.game.player1.name}'s turn`;
        case this.player2:
          return `${this.game.player2.name}'s turn`;
        default: 
          return ''
      }
    }
    return ''
  }

  getWinner(): string {
    if(!!this.game && this.game.winner) {
      switch(this.game.winner) {
        case this.player1:
          return `${this.game.player1.name}'s winner`;
        case this.player1:
          return `${this.game.player2.name}'s winner`;
        case this.tiedGame:
          return "It's a tie";
        default: 
          return ''
      }
    }
    return ''
  }

  // disable pit if the game is finished or current turn is opponent's or seeds are 0
  shouldDisablePit(turn: number, seeds: number): boolean {
    return !this.game || this.game.gameStatus === GameStatus.FINISHED || this.game.currentTurn === turn || seeds === 0
  }

  isPlayer1Turn(): boolean {
    return !!this.game && this.game.currentTurn === this.player1;
  }

  goBack(){
    this.gameService.clearGame();
    this.router.navigate(['/home']);
  }

}
