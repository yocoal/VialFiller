package vialFiller;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.TilePath;


public class WalkTo extends Task<ClientContext> {
	
	private int fountainIds;
	private final Tile[] fountainTile = { new Tile(3176,3487,0), new Tile(3189,3489,0),new Tile(3198,3481,0),new Tile(3192,3470)};
	
	public WalkTo(ClientContext ctx)
	{
		super(ctx);
	}
	
	@Override
	// activate if we have 28 empty vials and NO PUMP AVAILABLE
	public boolean activate()
	{
		boolean fountainPresent = false;
		// 24879 is the fountain #
	
	
		return( ctx.inventory.select().id(229).size()==28 && ctx.objects.select().id(24897).size()==0);
		
	}
	
	@Override
	public void execute()
	{

		ctx.movement.newTilePath(fountainTile).traverse();
	}
}
