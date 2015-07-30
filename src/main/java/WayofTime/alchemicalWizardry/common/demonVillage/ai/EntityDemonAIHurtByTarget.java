package WayofTime.alchemicalWizardry.common.demonVillage.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import WayofTime.alchemicalWizardry.common.demonVillage.demonHoard.demon.IHoardDemon;
import WayofTime.alchemicalWizardry.common.demonVillage.tileEntity.TEDemonPortal;

public class EntityDemonAIHurtByTarget extends EntityAIHurtByTarget
{
	boolean entityCallsForHelp;

	public EntityDemonAIHurtByTarget(EntityCreature demon, boolean callsForHelp) 
	{
		super(demon, callsForHelp);
		this.entityCallsForHelp = callsForHelp;
	}

	@Override
	public void startExecuting()
    {
		BlockPos portalPosition = ((IHoardDemon)this.taskOwner).getPortalLocation();
    	if(portalPosition == null)
    	{
    		super.startExecuting();
    		return;
    	}
    	
    	TileEntity portal = this.taskOwner.worldObj.getTileEntity(portalPosition);
    	
    	if((this.taskOwner.getAITarget() instanceof IHoardDemon && portalPosition.equals(((IHoardDemon)this.taskOwner.getAITarget()).getPortalLocation())))
    	{
    		return;
    	}
		
        this.taskOwner.setAttackTarget(this.taskOwner.getAITarget());

        if (this.entityCallsForHelp)
        {
        	
        	if(portal instanceof TEDemonPortal)
        	{	
        		((TEDemonPortal) portal).notifyDemons(taskOwner, this.taskOwner.getAITarget(), 25);
        	}
        	
//            double d0 = this.getTargetDistance();
//            List list = this.taskOwner.worldObj.getEntitiesWithinAABB(this.taskOwner.getClass(), new AxisAlignedBB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D).expand(d0, 10.0D, d0));
//            Iterator iterator = list.iterator();
//
//            while (iterator.hasNext())
//            {
//                EntityCreature entitycreature = (EntityCreature)iterator.next();
//
//                if (this.taskOwner != entitycreature && entitycreature.getAttackTarget() == null && !entitycreature.isOnSameTeam(this.taskOwner.getAITarget()))
//                {
//                    entitycreature.setAttackTarget(this.taskOwner.getAITarget());
//                }
//            }
        }

        super.startExecuting();
    }
}
