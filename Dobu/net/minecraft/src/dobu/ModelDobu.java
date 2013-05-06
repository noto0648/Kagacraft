package net.minecraft.src.dobu;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelDobu extends ModelBase
{
	//fields
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape5;
	ModelRenderer Shape4;

	public ModelDobu()
	{
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 4);
		Shape1.addBox(0F, 0F, 0F, 16, 1, 14);
		Shape1.setRotationPoint(-6F, 15F, -7F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(0F, 0F, 0F, 6, 1, 1);
		Shape2.setRotationPoint(-6F, 15F, -8F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 2);
		Shape3.addBox(0F, 0F, 0F, 6, 1, 1);
		Shape3.setRotationPoint(4F, 15F, -8F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 14, 0);
		Shape5.addBox(0F, 0F, 0F, 6, 1, 1);
		Shape5.setRotationPoint(4F, 15F, 7F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 14, 2);
		Shape4.addBox(0F, 0F, 0F, 6, 1, 1);
		Shape4.setRotationPoint(-6F, 15F, 7F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
	}
	
	public void modelRender(float f5)
	{
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape5.render(f5);
		Shape4.render(f5);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape5.render(f5);
		Shape4.render(f5);
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

}
