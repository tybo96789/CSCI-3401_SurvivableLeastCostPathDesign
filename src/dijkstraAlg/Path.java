package dijkstraAlg;

import Router.Router;
import java.util.ArrayList;

/**Path "Route" from Source to Destination
 *
 * @author Tyler Atiburcio 
 * @author Alter Calubana
 * @author Gabriel Fontanilla
 */
public class Path {

    protected double[] costHop;
    protected Router[] routers;
    private final Router SOURCE;
    private final Router DESTINATION;
    
    /**Creates a new Path "Route", which holds the next hops and their link cost
     * 
     * @param costHop double[]
     * @param routers Router[]
     */
    public Path(double[] costHop, Router[] routers) {
        this.costHop = costHop;
        this.routers = routers;
        this.SOURCE = this.routers[0];
        this.DESTINATION = this.routers[this.routers.length-1];
    }
    /**Creates a new Path "Route", which holds the next hops and their link cost
     * 
     * @param costHop ArrayList<Double>
     * @param path ArrayList<Router>
     */
    public Path(ArrayList<Double> costHop, ArrayList<Router> path)
    {
        this.costHop = new double[costHop.size()];
        this.routers = new Router[path.size()];
        for (int i = 0; i < this.routers.length; i++) {
            this.costHop[i] = costHop.get(i);
            this.routers[i] = path.get(i);
        }
        
        this.SOURCE = this.routers[0];
        this.DESTINATION = this.routers[this.routers.length-1];
    }
    
    /**Returns the total cost of the path to source to destination
     * 
     * @return double; Cost of path
     */
    public double getTotalCost()
    {
        int cost = 0;
        for (int i = 0; i < costHop.length; i++) {
            cost += costHop[i];
        }
        return cost;
    }
    
    /**Returns the array of 'Next Hops'
     * 
     * @return Routers; Next Hops
     */
    public Router[] getRouterPath()
    {
        return this.routers;
    }

    @Override
    public String toString()
    {
        String str = this.SOURCE.getName() + "<->"+ this.DESTINATION.getName() + "\n[";
        for (Router r : routers) 
            str += r.getName() + ", ";
        str += "]\n[";
        for (double i : costHop) 
            str+=i +", ";
        str+= "]";
        return str;
    }
    
    
    
    /**Calculate which links are used throughout each Router
     * 
     * @param routerList
     * @param path
     * @return int[][] Links used in the 2 paths
     */
    public static int[][] calculateLinksUsed(ArrayList<Router> routerList, Path path)
    {
        int[][] usedLinks = new int[routerList.size()][routerList.size()];
        for (int i = 0; i < path.getRouterPath().length-1; i++) {
            
           int routerIndex = 0;
           for(;routerIndex < routerList.size();routerIndex++)
               if(path.getRouterPath()[i].equals(routerList.get(routerIndex)))
                   break;
           int linkIndex = 0;
           for(;linkIndex < routerList.get(routerIndex).getLinks().size();linkIndex++)
               if(routerList.get(routerIndex).getLinks().get(linkIndex).getB().equals(path.getRouterPath()[i + 1]))
                   break;
           usedLinks[routerIndex][linkIndex]++;
        }
       
        
        return usedLinks;
    }
    /**Return the source router
     * 
     * @return RouterSource
     */
    public Router getSOURCE() {
        return SOURCE;
    }
    
    /**Return the destination router
     * 
     * @return RouterDestination
     */
    public Router getDESTINATION() {
        return DESTINATION;
    }
    
}
