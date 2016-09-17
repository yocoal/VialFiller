package vialFiller;

import org.powerbot.script.Interactive;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;


public class Fill extends Task<ClientContext> {
	
	int emptyVialID = 229;
	
	Tile fountainTile  = new Tile(3192,3470);
	
	
	public Fill(ClientContext ctx)
	{
		super(ctx);
	}

	// active if we have at least 1 empty vials, pump available, within two tiles of fill

	@Override
	public boolean activate()
	{
		/** random error checking stuff
		if(ctx.inventory.select().id(229).size()>=1)
			System.out.print("True ");
		else
			System.out.print("False ");
		
		if(ctx.objects.select().id(24897).size()>=1)
			System.out.print("True ");
		else
			System.out.print("False ");
		
		if(ctx.movement.distance(fountainTile)<=2)
			System.out.println("True");
		else
			System.out.println("False");
		**/
		return (ctx.inventory.select().id(229).size() >=1 && ctx.objects.select().id(5125).size()>=1 && ctx.movement.distance(fountainTile)<=5);
	}

	@Override
	public void execute() 
	{
		System.out.println("Got In 2");
		ctx.inventory.id(emptyVialID).peek().interact("Use");
		GameObject fountain  = ctx.objects.select().name("Fountain").nearest().poll();
		if(fountain.inViewport())
		{
			fountain.interact("Use");
		}
			
			
	}
}
