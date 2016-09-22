package marbletables.vialfiller;

import org.powerbot.script.rt4.Bank.Amount;
import org.powerbot.script.rt4.ClientContext;

public class Bank extends Task<ClientContext>{
	
	private int bankIds;
	private int emptyVialId = 229;
	
	
	public Bank(ClientContext ctx)
	{
		super(ctx);
	}
	
	
	/**
	 * if inventory does not have 28 empty vials, and a bank is nearby
	 */
	@Override
	public boolean activate()
	{
		return (ctx.inventory.select().id(emptyVialId).size()< 28 && ctx.bank.present());
	}
	
	/**
	 * deposit everything and withdraw empty vials
	 */
	@Override
	public void execute()
	{
		if(!ctx.bank.opened())
		{
				ctx.bank.open();
				return;
		}
		
		// if # vials inventory does not equal total # items in inventory 
		// we know we have non empty vials or other random items in your inventory
		// so deposit all of these items if this is the case
		if(ctx.inventory.select().count()>=1 && ctx.inventory.select().id(emptyVialId).count() != ctx.inventory.select().count())
		{
			ctx.bank.depositInventory();
			return;
		}
		
		// if you only have empty vials in your inventory ( also includes if you have NO Items in inventory)
		// withdraw all empty vials
		if(ctx.inventory.select().count() == ctx.inventory.select().id(emptyVialId).count() && ctx.inventory.select().id(emptyVialId).count() <28)
		{
			ctx.bank.withdraw(emptyVialId, Amount.ALL);
		}
		

		// close the bank if you have 28 empty vials
		if(ctx.inventory.select().id(emptyVialId).count() == 28)
		{
			ctx.bank.close();
		}
	}

}
