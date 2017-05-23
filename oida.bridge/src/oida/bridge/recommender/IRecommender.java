package oida.bridge.recommender;

/**
 * 
 * @author Michael Shamiyeh
 * @since 2017-03-09
 *
 */
public interface IRecommender {
	String getName();
	
	/**
	 * Sets a flag, which determines, if the recommender is active.
	 * 
	 * @param active true, if the recommender should be active, otherwise false.
	 */
	void setActive(boolean active);
	
	/**
	 * Gives information, if the recommender is currently active.
	 * 
	 * @return true, if the recommender is active.
	 */
	boolean isActive();
}
