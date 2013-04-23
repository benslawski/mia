/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;

    /** Initialize a map of all open waypoints and their locations. **/
    private Map<Location, Waypoint> open_waypoints
    = new HashMap<Location, Waypoint> ();
    
    /** Initialize a map of all closed waypoints and their locations. **/
    private Map<Location, Waypoint> closed_waypoints
    = new HashMap<Location, Waypoint> ();

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /** TODO
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        if (numOpenWaypoints == 0)
            return null;
        
        Set set = open_waypoints.entrySet();
        Iterator i = set.iterator();
        
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            // Something about .getTotalCost()
            // Return reference to waypoint with smallest total cost
            
        }
        
    }

    /** TODO
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        if (numOpenWaypoints == 0)
          //  open_waypoints.put(getLocation(), hashcode
        
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        return open_waypoints.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        open_waypoints.remove(Location loc);
 //TODO       closed_waypoints.put(Location loc, ??);
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        closed_waypoints.containsKey(Location loc);
        
        // If we got here then the collection of closed waypoints
        // does not contain a waypoint for the specified location.
        return false;
    }
}

