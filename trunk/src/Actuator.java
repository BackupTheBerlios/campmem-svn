/**
 */
public abstract class Actuator implements IStateListener
{
	/**
	 */
	public Actuator()
	{
	}

	/**
	 */
	public abstract boolean isActingFor(State s);

	/**
	 */
	public abstract void act(State s);

	/**
	 */
	public abstract void stateUpdated(State s);
}