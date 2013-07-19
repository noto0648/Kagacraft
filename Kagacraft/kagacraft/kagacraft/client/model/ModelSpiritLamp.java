package kagacraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSpiritLamp extends ModelBase
{
	//fields
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;

	public ModelSpiritLamp()
	{
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, -1F, 0F, 8, 4, 8);
		Shape1.setRotationPoint(-3F, 20F, -5F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 56, 14);
		Shape2.addBox(0F, 0F, 0F, 2, 15, 2);
		Shape2.setRotationPoint(-7F, 8F, -4F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 38, 4);
		Shape3.addBox(0F, 0F, 0F, 2, 3, 2);
		Shape3.setRotationPoint(0F, 16F, -2F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 30, 0);
		Shape4.addBox(0F, 0F, 0F, 15, 2, 2);
		Shape4.setRotationPoint(-7F, 9F, -2F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0.1919862F);
		Shape5 = new ModelRenderer(this, 0, 15);
		Shape5.addBox(0F, 0F, 0F, 16, 1, 16);
		Shape5.setRotationPoint(-8F, 23F, -8F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 46, 4);
		Shape6.addBox(0F, 0F, 0F, 2, 1, 2);
		Shape6.setRotationPoint(-7F, 11F, -2F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0.1919862F);
		Shape7 = new ModelRenderer(this, 46, 4);
		Shape7.addBox(0F, 0F, 0F, 2, 1, 2);
		Shape7.setRotationPoint(-7F, 8F, -2F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0.1919862F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}

	public void allRender(float f5, boolean par1)
	{
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		if(par1)
		{
			Shape4.render(f5);
		}
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
	}
}
