package BlackJack.model;

public class Game {

  private Dealer m_dealer;
  private Player m_player;
  private java.util.ArrayList<BlackJackObserver> m_subscribers;

  public Game() {
    m_dealer = new Dealer(new BlackJack.model.rules.RulesFactory());
    m_player = new Player();
    m_subscribers = new java.util.ArrayList<BlackJackObserver>();
  }
    
  public boolean IsGameOver() { return m_dealer.IsGameOver(); }
  
  public boolean IsDealerWinner() { return m_dealer.IsDealerWinner(m_player); }
  
  public boolean NewGame() { return m_dealer.NewGame(m_player); }
  
  public boolean Hit() { return m_dealer.Hit(m_player); }
  
  public boolean Stand() { return m_dealer.Stand(); }
  
  public Iterable<Card> GetDealerHand() { return m_dealer.GetHand(); }
  
  public Iterable<Card> GetPlayerHand() { return m_player.GetHand(); }
  
  public int GetDealerScore() { return m_dealer.CalcScore(); }
  
  public int GetPlayerScore() { return m_player.CalcScore(); }
  
  public void AddSubscriber(BlackJackObserver a_sub) { m_dealer.AddSubscriber(a_sub); }
}