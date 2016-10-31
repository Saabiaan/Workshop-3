package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.model.Game;

public class PlayGame implements BlackJack.model.BlackJackObserver{

	private BlackJack.view.IView m_view;
	private BlackJack.model.Game m_game;
	
  public PlayGame(Game a_game, IView a_view) {
	  m_view = a_view;
	  m_game = a_game;
	  m_game.AddSubscriber(this);
  }
  	public boolean Play() {
  		showInfoAndCards();

  		if (m_game.IsGameOver())
  			m_view.DisplayGameOver(m_game.IsDealerWinner());

    	int input = m_view.GetInput();
    
    	if (input == 'p')
    		m_game.NewGame();
    	else if (input == 'h')
    		m_game.Hit();
    	else if (input == 's')
    		m_game.Stand();
    
    	return input != 'q';
  	}
  
  	public void cardDealt() {
  		try { 
  			//brief pause.
  			Thread.sleep(2000); 
  			//Show hand.
  			showInfoAndCards();
  		}
  		catch (InterruptedException e) {
  			System.out.println("test");
  		}
  	}
  	
  	//Method for showing information and dealer/players hand so that it is easily called from cardInHand().
  	public void showInfoAndCards() {
  		m_view.DisplayWelcomeMessage();
  		m_view.DisplayDealerHand(m_game.GetDealerHand(), m_game.GetDealerScore());
  		m_view.DisplayPlayerHand(m_game.GetPlayerHand(), m_game.GetPlayerScore());
  	}
}