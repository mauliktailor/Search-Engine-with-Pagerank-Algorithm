package textprocessing;

import java.util.*;
import java.io.*;

public class PageRank {
	 
		public int path[][] ;
		public double pagerank[] ;
		public int size;
		PageRank(int size) {
			this.size = size;
			path = new int[size][size];
			pagerank = new double[size];
		}
		 
		public  void calc(double totalNodes){
		    
		double InitialPageRank;
		double OutgoingLinks=0; 
		double DampingFactor = 0.85; 
		double TempPageRank[] = new double[size];
		
		int outer;
		int inner; 
		int k=1; // For Traversing
		int ITR=1;
		
		InitialPageRank = 1/totalNodes;
		System.out.printf(" Total Number of Nodes :"+totalNodes+"\t Initial PageRank  of All Nodes :"+InitialPageRank+"\n");
		 
		// Initialization of page rank for each nodes
		for(k=0;k<totalNodes;k++)
		{
		  pagerank[k]=InitialPageRank;
		}   
		  
		System.out.printf("\n Initial PageRank Values , 0th Step \n");
		for(k=0;k<totalNodes;k++)
		{
		  System.out.printf(" Page Rank of "+k+" is :\t"+pagerank[k]+"\n");
		}  
		  
		 while(ITR<=2) // Iterations
		 {
			   // Store the PageRank for All Nodes in Temporary Array 
			  for(k=0;k<totalNodes;k++)
			 {  
				  TempPageRank[k]=pagerank[k];
				  pagerank[k]=0;
			  }
			    
			  
			 for(inner=0;inner<totalNodes;inner++)
			 {
				  for(outer=0;outer<totalNodes;outer++)
				   {
					    if(path[outer][inner] == 1)
					    { 
					      k=1;
					      OutgoingLinks=0;  // Count the Number of Outgoing Links for each outer
					      while(k<totalNodes)
					      {
						        if(path[outer][k] == 1 )
						        {
						        	OutgoingLinks=OutgoingLinks+1; // Counter for Outgoing Links
						        }  
						        k=k+1;  
					      } 
					         // Calculate PageRank     
					         pagerank[inner]+=TempPageRank[outer]*(1/OutgoingLinks);    
					     }
				   }  
			 }    
		     
			 System.out.printf("\n After "+ITR+"th Step \n");
		  
		     for(k=0;k<totalNodes;k++) 
		    	 System.out.printf(" Page Rank of "+k+" is :\t"+pagerank[k]+"\n"); 
		  
		     ITR = ITR+1;
		}
		
		// Add the Damping Factor to PageRank
		for(k=0;k<totalNodes;k++)
		{
		  pagerank[k]=(1-DampingFactor)+ DampingFactor*pagerank[k]; 
		} 
		  
		// Display PageRank
		System.out.printf("\n Final Page Rank : \n"); 
		for(k=0;k<totalNodes;k++)
		{
			System.out.printf(" Page Rank of "+k+" is :\t"+pagerank[k]+"\n"); 
		}
		
	 } 

	 public static void main(String args[])
	    {
	        int nodes,i,j,cost;
	        Scanner in = new Scanner(System.in);
	        System.out.println("Enter the Number of WebPages \n");
	        nodes = in.nextInt();
	        PageRank p = new PageRank(10);
	        System.out.println("Enter the Adjacency Matrix with 1->PATH & 0->NO PATH Between two WebPages: \n");
	        for(i=1;i<=nodes;i++)
	        {
		          for(j=1;j<=nodes;j++)
		          {
			            p.path[i][j]=in.nextInt();
			            if(j==i)
			              p.path[i][j]=0;
		          }
	        }
	        
	        p.calc(nodes);     
	    }   

	}

