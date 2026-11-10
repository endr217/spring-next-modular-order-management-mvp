import { createProduct } from "@/modules/products/actions/create-product";
import { ProductForm } from "@/modules/products/components/product-form";
import { getCategories } from "@/modules/products/queries/get-categories";

export async function NewProduct() {
  const categories = await getCategories();

  return <ProductForm action={createProduct} categories={categories} />;
}
