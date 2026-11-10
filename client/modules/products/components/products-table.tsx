import { useTranslations } from "next-intl";
import { DataTable } from "@/shared/components/data-table";
import { EmptyRow } from "@/shared/components/empty-row";
import { ProductRow } from "@/modules/products/components/product-row";
import type { Product } from "@/modules/products/types";

export function ProductsTable({ products }: { products: Product[] }) {
  const t = useTranslations("products");
  const head = [t("fields.name"), t("fields.category"), t("fields.price"), t("fields.stock"), ""];

  return (
    <DataTable head={head}>
      {products.length === 0 && <EmptyRow colSpan={head.length} text={t("empty")} />}
      {products.map((product) => (
        <ProductRow key={product.id} product={product} />
      ))}
    </DataTable>
  );
}
