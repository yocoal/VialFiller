package vialFiller;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(name ="GE Vial Filler", description="Fills vials at GE", properties = "author=guy;topic=1234123;client=4;version=1;")
public class VialFiller extends PollingScript<ClientContext>{

	private List<Task> taskList = new ArrayList<Task>();
	
	@Override
	public void start()
	{
		taskList.addAll(Arrays.asList(new Deposit(ctx), new Withdraw(ctx), new WalkTo(ctx), new Fill(ctx), new WalkBack(ctx)));
	}
	
	public void poll()
	{
		for(Task task: taskList)
		{
			if(task.activate())
			{
				task.execute();
			}
		}
		
	}
}
