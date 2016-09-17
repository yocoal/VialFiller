package vialFiller;

import org.powerbot.script.rt4.ClientContext;

public class Withdraw extends Task<ClientContext>{
	
	private int bankIds;
	private int vialIds;
	
	public Withdraw(ClientContext ctx)
	{
		super(ctx);
	}
	
	
	/**
	 * if inventory is NOT full, and a bank is nearby
	 */
	@Override
	public boolean activate()
	{
		return (ctx.inventory.select().size()< 28 && ctx.bank.present());
	}
	
	/**
	 * deposit everything and withdraw empty vials
	 */
	@Override
	public void execute()
	{
		ctx.bank.open();
		ctx.bank.depositInventory();
		ctx.bank.withdraw(229, 28);
		ctx.bank.close();
	}

}
