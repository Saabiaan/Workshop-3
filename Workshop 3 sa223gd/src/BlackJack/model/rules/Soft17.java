package BlackJack.model.rules;

import BlackJack.model.Card;

public class Soft17 implements IHitStrategy {
	private final int g_hitLimit = 17;
	
	public boolean DoHit(BlackJack.model.Player a_dealer) {
		int score = 0;
		
		if (a_dealer.CalcScore() ==17) {
			for (BlackJack.model.Card c : a_dealer.GetHand()) {
				if (c.GetValue() == Card.Value.Ace)
					score += 0;
				else
					score += c.GetValue().ordinal();
			}
			if (score == 6)
				return true;	
		}
		return a_dealer.CalcScore() < g_hitLimit;
	}
}
