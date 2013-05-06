package net.minecraft.src.globe;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelGlobe extends ModelBase
{
	//fields
	ModelRenderer Shape1;
	ModelRenderer Shape3;
	ModelRenderer Shape2;

	public ModelGlobe()
	{
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 6, 1, 6);
		Shape1.setRotationPoint(-3F, 23F, -3F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 7);
		Shape3.addBox(-5F, -5F, -5F, 10, 10, 10);
		Shape3.setRotationPoint(0F, 14F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 18, 0);
		Shape2.addBox(0F, 0F, 0F, 2, 4, 2);
		Shape2.setRotationPoint(-1F, 19F, -1F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		
		

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape3.render(f5);
		Shape2.render(f5);
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
	
	public void renderAll(float f5)
	{
		Shape1.render(f5);
		Shape3.render(f5);
		Shape2.render(f5);

		float c = (float)(System.currentTimeMillis() % 8000) / 180;
		setRotation(Shape3, 0f, c, 0f);
	}

}
