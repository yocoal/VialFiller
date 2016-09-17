package vialFiller;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;


public class Deposit extends Task<ClientContext>
{
	
	private int bankIds;
	private int vialIds;
	private int filledVialIds;
	
	public Deposit(ClientContext ctx)
	{
		super(ctx);
	}
	
	// if full inventory, no empty vials, bank is nearby
	// not sure how to do the "is a bank near?" check
	@Override
	public boolean activate()
	{
		return (ctx.inventory.select().size() == 28 && ctx.inventory.select().id(229).size() <28 && ctx.bank.present());
		
	}
	
	// for now deposit everything
	@Override
	public void execute()
	{
		ctx.bank.open();
		ctx.bank.depositInventory();
		ctx.bank.withdraw(229, 28);
		ctx.bank.close();
	}
}

	
	
	
