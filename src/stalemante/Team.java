package stalemante;
import java.awt.Color;

public abstract class Team {
	public abstract String name();
	public abstract Color color();

	@Override
	public String toString() {
		return name();
	}
}
