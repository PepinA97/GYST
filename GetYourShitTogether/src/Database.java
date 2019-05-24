
abstract class Database<M extends Media> {
	
		protected FilePath fp = new FilePath();
		
		protected String filePath = fp.getFilePath();
		
		protected abstract void connect();
		
		protected abstract void close();
		
		protected abstract void Insert (M m);

}
