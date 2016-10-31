package BlackJack.model;

import BlackJack.model.rules.*;
import java.util.*;

public class Dealer extends Player {

  private Deck m_deck;
  private INewGameStrategy m_newGameRule;
  private IHitStrategy m_hitRule;
  private IWinner m_winner;

  private java.util.ArrayList<BlackJackObserver> m_observer;
  
  public Dealer(RulesFactory a_rulesFactory) {
	  m_newGameRule = a_rulesFactory.GetNewGameRule();
	  m_hitRule = a_rulesFactory.GetHitRule();
	  m_winner = a_rulesFactory.GetWinnerRule();
	  m_observer = new java.util.ArrayList<BlackJackObserver>();
  }
  
  public void Deal(Player a_player, boolean showCard) {
	  Card c = m_deck.GetCard();
	  c.Show(showCard);
	  a_player.DealCard(c);
	  
	  //Observer
	  for (BlackJackObserver obs : m_observer)
		  obs.cardDealt();
  }
  
  public void AddSubscriber(BlackJackObserver a_sub) {
	  m_observer.add(a_sub);
  }
  
  public boolean NewGame(Player a_player) {
	  if (m_deck == null || IsGameOver()) {
		  m_deck = new Deck();
		  ClearHand();
		  a_player.ClearHand();
		  return m_newGameRule.NewGame(m_deck, this, a_player);   
	  }
	  return false;
  }

  public boolean Hit(Player a_player) {
	  if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver()) {
		  Deal(a_player, true);
		  return true;
	  }
	  return false;
  }

  public boolean IsDealerWinner(Player a_player) {
	  if (a_player.CalcScore() > g_maxScore) 
		  return true;
	  else if (CalcScore() > g_maxScore) 
		  return false;
  
	  //Check rule.
	  return m_winner.IsWinner(this, a_player);
  }

  public boolean IsGameOver() {
	  if (m_deck != null && m_hitRule.DoHit(this) != true)
		  return true;

	  return false;
  }
  
  public boolean Stand() {
	  if (m_deck != null) {
		  ShowHand();
		  //Show cards
		  for (Card c : GetHand()) 
			  c.Show(true);
		  
		  //Check for hit rule and possibly deal a card.
		  while (m_hitRule.DoHit(this)) 
			  Deal(this, true);
		  
		  return true;
	  }
	  return false;
  }
}







