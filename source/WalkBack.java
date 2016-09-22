package marbletables.vialfiller;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.TilePath;

public class WalkBack extends Task<ClientContext> {

	int vialOfWaterId = 227;
	private Tile[] walkBackPathTiles = {new Tile(3196, 3474), new Tile(3196, 3481), new Tile(3192,3491),new Tile(3184,3491), new Tile(3178,3492), new Tile(3170, 3488)};
	
	public WalkBack(ClientContext ctx)
	{
		super(ctx);
	}

	@Override
	// if we have 28 filled vials walkback
	public boolean activate()
	{
		return ctx.inventory.select().id(vialOfWaterId).size()==28;
	}

	@Override
	public void execute()
	{
		while(ctx.movement.newTilePath(walkBackPathTiles).traverse())
		{
			ctx.movement.newTilePath(walkBackPathTiles).traverse();
		}
	}
	
	

}
