package Paper;

public interface Iterator<O> {
	
	public boolean hasNext();
	public <O> O next();
	boolean hasNext(Paper page);
}
