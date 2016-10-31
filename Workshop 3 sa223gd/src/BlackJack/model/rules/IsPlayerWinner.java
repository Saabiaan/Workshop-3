package BlackJack.model.rules;

public class IsPlayerWinner implements IWinner {
	public boolean IsWinner(BlackJack.model.Player a_dealer, BlackJack.model.Player a_player) {
		return (a_dealer.CalcScore() >= a_player.CalcScore());
	}
}
