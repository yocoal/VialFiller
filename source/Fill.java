package marbletables.vialfiller;

import org.powerbot.script.Condition;
import org.powerbot.script.Interactive;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.Condition;


public class Fill extends Task<ClientContext> {
	
	int vialId = 229;
	int vialOfWaterId = 227;
	int fountainId = 5125;
	
	Tile fountainTile  = new Tile(3192,3470);
	
	public Fill(ClientContext ctx)
	{
		super(ctx);
	}

	// active if we have at least 1 empty vials, pump available, within three tiles of fill

	@Override
	public boolean activate()
	{
		return (ctx.inventory.select().id(vialId).size() >=1 && ctx.objects.select(fountainTile,3).id(fountainId).size() >= 1);
	}

	@Override
	public void execute() 
	{
		//
	
		GameObject fountain  = ctx.objects.peek();
		Item vial = ctx.inventory.poll();
		
		// if we're already filling vials no need to click "Use" a million times
		// so sleep for 20 seconds while vials are filling up
		if(fountain.inViewport() && vial.valid() )
		{
			vial.interact("Use");
			fountain.interact("Use");
			Condition.sleep(20000);
			
		}
		
		return;
			
			
	}
}
