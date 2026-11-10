import { updateProduct } from "@/modules/products/actions/update-product";
import { ProductForm } from "@/modules/products/components/product-form";
import { getCategories } from "@/modules/products/queries/get-categories";
import { getProduct } from "@/modules/products/queries/get-product";

export async function EditProduct({ params }: Pick<PageProps<"/products/[id]/edit">, "params">) {
  const { id } = await params;
  const [product, categories] = await Promise.all([getProduct(id), getCategories()]);

  return <ProductForm action={updateProduct.bind(null, product.id)} categories={categories} product={product} />;
}
