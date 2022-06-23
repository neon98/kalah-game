import { GameStatus } from "./gameStatus.enum";
import { Player } from "./player";

export interface Game{
    id: string;
    player1: Player;
    player2: Player;
    gameStatus: GameStatus;
    currentTurn: number;
    winner: number;
    board: number[];
}