import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { ApiResponse } from '../models/dto/apiResponse';
import { StartGameRequestDto } from '../models/dto/startGameRequestDto';
import { Game } from '../models/game';
import { PlayGameRequestDto } from '../models/dto/playGameRequestDto';
import { GameStatus } from '../models';

const BASE_URL = "http://localhost:8080/game";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private game: BehaviorSubject<Game> = new BehaviorSubject<Game>({} as Game);
  game$: Observable<Game> = this.game.asObservable();

  private loader: BehaviorSubject<Boolean> = new BehaviorSubject<Boolean>(false);
  loader$: Observable<Boolean> = this.loader.asObservable();

  constructor(private http: HttpClient) {
    // this.game.next({"id":"7ce54e71-006f-4a91-a32c-3a5619993171","player1":{"name":"a","kalahIndex":6},"player2":{"name":"b","kalahIndex":13},"gameStatus":GameStatus.IN_PROGRESS,"currentTurn":1,"winner": 0, "board":[4,4,4,4,4,4,0,4,4,4,4,4,4,0]})
  }

  startGame(req: StartGameRequestDto): void{
    this.http.post<ApiResponse<Game>>(`${BASE_URL}/start`, req).subscribe(res => {
      this.game.next(res.data);
    });
  }  

  playGame(req : PlayGameRequestDto) {
    this.http.post<ApiResponse<Game>>(`${BASE_URL}/play`, req).subscribe(res => {
      this.game.next(res.data);
    });
  }

  resetGame(req : string) {
    this.http.post<ApiResponse<Game>>(`${BASE_URL}/reset`, req).subscribe(res => {
      this.game.next(res.data);
    });
  }
  
  clearGame(){
    this.game.next({} as Game);
  }
}
