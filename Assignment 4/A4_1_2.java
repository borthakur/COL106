    import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;


public class A4_2019MT60493 {
	
	public class edge{
		public int d;
		public int w;
		
		public edge(int d,int w) {
			this.d = d;
			this.w = w;
		}
	}
	
	public class occur{
		public int node;
		public long val;
		public occur(int node,long val) {
			this.node = node;
			this.val = val;
		}
	}
	
	public class graph{
		
		Vector<String> int2name;
		Vector<Vector<edge>> edges;
		Map<String,Integer> name2int;
		
		int n;
		int m;
		
		public graph(String nodesFile,String edgesFile) throws Exception{
			n = 0;
			m = 0;
			
			name2int = new HashMap<>();
			int2name = new Vector<String>();
			
			File fl1 = new File(nodesFile);
			BufferedReader reader = new BufferedReader(new FileReader(fl1)); 
			
			String input = reader.readLine();
			
			while ((input = reader.readLine()) != null) {
					String[] a = input.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
					a[0] = a[0].replace("\"", "");
					name2int.put(a[0],n);
					int2name.add(a[0]);
					n++;
			}
			
			reader.close();
			
			edges = new Vector<Vector<edge>>();
			edges.setSize(n);
			
			File fl2 = new File(edgesFile);
			reader = new BufferedReader(new FileReader(fl2));

			input = reader.readLine();

			while((input = reader.readLine())!=null) {		
					String[] a = input.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
					
					a[0] = a[0].replace("\"", "");					
					a[1] = a[1].replace("\"", "");
					
					edge e1 = new edge(name2int.get(a[1]),Integer.valueOf(a[2]));
					edge e2 = new edge(name2int.get(a[0]),Integer.valueOf(a[2]));
					
					if(edges.get(name2int.get(a[0])) != null) edges.get(name2int.get(a[0])).add(e1);
					else {
						Vector<edge> temp = new Vector<edge>();
						temp.add(e1);
						edges.set(name2int.get(a[0]),temp);
					}
					
					if(edges.get(name2int.get(a[1])) != null) edges.get(name2int.get(a[1])).add(e2);
					else {
						Vector<edge> temp = new Vector<edge>();
						temp.add(e2);
						edges.set(name2int.get(a[1]),temp);
					}
					
					m++;
			}
			
			reader.close();
		}
		
		public occur compare(occur x,occur y) {
			if(x.val>y.val) return x;
			if(y.val>x.val) return y;
			if(int2name.get(x.node).compareTo(int2name.get(y.node))>0) return x;
			return y;
		}
		
		public void printAverage() {
			float avg = 2*(float)m/n;
			System.out.format("%.2f",avg);
		}
		
		public void Sort(Vector<occur> occurs) {
			Sort(occurs,0,n-1);
		}
		
		public void Sort(Vector<occur> occurs, int l, int r) {
			if(r<=l) return;
			int m = (l+r)/2;
			Sort(occurs,l,m);
			Sort(occurs,m+1,r);
			Vector<occur> temp = new Vector<occur>(r-l+1);
			temp.setSize(r-l+1);
			int c1 = l;
			int c2 = m+1;
			int c = 0;
			while(c1<=m && c2<=r) {
				if(occurs.get(c1) == compare(occurs.get(c1),occurs.get(c2))) {
					temp.set(c, occurs.get(c1));
					c++;
					c1++;
				}
				else {
					temp.set(c, occurs.get(c2));
					c++;
					c2++;
				}
			}
			while(c1<=m) {
				temp.set(c, occurs.get(c1));
				c++;
				c1++;
			}
			while(c2<=r) {
				temp.set(c, occurs.get(c2));
				c++;
				c2++;
			}
			c=0;
			while(c<r-l+1) {
				occurs.set(c+l, temp.get(c));
				c++;
			}
		}
		
		public void printRank() {
			Vector<occur> occurs = new Vector<occur>(n);
			occurs.setSize(n);
			
			for(int i=0;i<n;i++) {
				long sum = 0;
				if(edges.get(i) != null){
					for(edge e:edges.get(i)) {
						sum += e.w;
					}
				}
				occur entry = new occur(i,sum);
				occurs.set(i, entry);
			}
			
			Sort(occurs);
			
			int flag = 0;
			for(int i=0;i<n;i++) {
				if(flag != 0) System.out.print(",");
				else flag = 1;
				System.out.format("%s",int2name.get(occurs.get(i).node));
			}
		}
		
		public void sortAStory(Vector<Integer> story) {
			sortAStoryRec(story,0,story.size()-1);
		}
		
		public void sortAStoryRec(Vector<Integer> story, int l, int r) {
			if(r<=l) return;
			int m = (l+r)/2;
			sortAStoryRec(story,l,m);
			sortAStoryRec(story,m+1,r);
			Vector<Integer> temp = new Vector<Integer>();
			temp.setSize(r-l+1);
			int c1 = l;
			int c2 = m+1;
			int c = 0;
			while(c1<=m && c2<=r) {
				if(int2name.get(story.get(c1)).compareTo(int2name.get(story.get(c2)))>0) {
					temp.set(c, story.get(c1));
					c1++;
					c++;
				}
				else {
					temp.set(c, story.get(c2));
					c2++;
					c++;
				}
			}
			while(c1<=m) {
				temp.set(c, story.get(c1));
				c1++;
				c++;
			}
			while(c2<=r) {
				temp.set(c, story.get(c2));
				c++;
				c2++;
			}
			c = 0;
			while(c<r-l+1) {
				story.set(c+l, temp.get(c));
				c++;
			}
		}
		
		public void outerSort(Vector<Vector<Integer>> stories) {
			outerSortRec(stories,0,stories.size()-1);
		}
		
		public void outerSortRec(Vector<Vector<Integer>> stories,int l,int r) {
			if(r<=l) return;
			int m = (l+r)/2;
			outerSortRec(stories,l,m);
			outerSortRec(stories,m+1,r);
			Vector<Vector<Integer>> temp = new Vector<Vector<Integer>>();
			temp.setSize(r-l+1);
			int c1 = l;
			int c2 = m+1;
			int c = 0;
			while(c1<=m && c2<=r) {
				if(stories.get(c1).size() > stories.get(c2).size() || (stories.get(c1).size() == stories.get(c2).size() && int2name.get(stories.get(c1).get(0)).compareTo(int2name.get(stories.get(c2).get(0))) > 0)) {
					temp.set(c, stories.get(c1));
					c1++;
					c++;
				}
				else {
					temp.set(c, stories.get(c2));
					c2++;
					c++;
				}
			}
			while(c1<=m) {
				temp.set(c, stories.get(c1));
				c1++;
				c++;
			}
			while(c2<=r) {
				temp.set(c, stories.get(c2));
				c++;
				c2++;
			}
			c = 0;
			while(c<r-l+1) {
				stories.set(c+l, temp.get(c));
				c++;
			}
		}
		
		public void sortStories(Vector<Vector<Integer>> stories) {
			for(Vector<Integer> story:stories) {
				sortAStory(story);
			}
			outerSort(stories);
		}
		
		public void DFS(Vector<Integer> temp,Vector<Integer> visited,int i) {
			visited.set(i, 1);
			temp.add(i);
			if(edges.get(i) == null) return;
			for(edge e:edges.get(i)) {
				if(visited.get(e.d) == 0) {
					DFS(temp,visited,e.d);
				}
			}
		}
		
		public void printStorylines() {
			Vector<Vector<Integer>> stories = new Vector<Vector<Integer>>();
			
			Vector<Integer> visited = new Vector<Integer>();
			for(int i=0;i<n;i++) {
				visited.add(0);
			}
			
			for(int i=0;i<n;i++) {
				if(visited.get(i) == 0) {
					Vector<Integer> temp = new Vector<Integer>();
					DFS(temp,visited,i);
					stories.add(temp);
				}
			}
			sortStories(stories);
			
			for(Vector<Integer> story:stories) {
				int flag = 0;
				for(Integer c:story) {
					if(flag != 0) System.out.print(",");
					else flag = 1;
					System.out.print(int2name.get(c));
				}
				System.out.println();
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception  {
		
		graph a4;
		A4_2019MT60493 x = new A4_2019MT60493();
		a4 = x.new graph(args[0],args[1]);
		
		if(args[2].compareTo("average") == 0){
			a4.printAverage();
		}
		else if(args[2].compareTo("rank") == 0) {
			a4.printRank();
		}
		else if(args[2].compareTo("independent_storylines_dfs") == 0) a4.printStorylines();
		
	}
}
