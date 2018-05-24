package repository;

public class RepositoryFactory {
	
	 public Repository getRepository(String repType){
	      if(repType == null){
	         return null;
	      }		
	      if(repType.equalsIgnoreCase("FILE")){
	         return new FileRepository();
	         
	      } else if(repType.equalsIgnoreCase("DB")){
	         return new DbRepository();
	         
	      }
	      
	      return null;
	   }

}
