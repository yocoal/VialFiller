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
		Item vial = ctx.inventory.peek();
		
		
		// make sure fountain is in view, and vial about to selected is valid
		// "Use" vial if we already haven't selected a vial
		if(fountain.inViewport() && vial.valid() && !ctx.inventory.selectedItem().valid())
		{
			vial.interact("Use");
			return;
		}
		
		// same as above , ensure that fountain is in viewport and selected vial is valid
		// also make sure that we've already selected vial
		// then select the fountain and wait 20 seconds until vials are filled with water
		if(fountain.inViewport() && vial.valid() && ctx.inventory.selectedItem().id() == vialId)
		{
			fountain.interact("Use");
			Condition.sleep(20000);
			
			return;	
		}
		
		return;
		
			
			
	}
}
