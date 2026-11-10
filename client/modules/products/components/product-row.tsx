import { useTranslations } from "next-intl";
import { deleteProduct } from "@/modules/products/actions/delete-product";
import { DeleteButton } from "@/shared/components/delete-button";
import { LinkButton } from "@/shared/components/link-button";
import { Money } from "@/shared/components/money";
import { TableCell, TableRow } from "@/shared/components/ui/table";
import type { Product } from "@/modules/products/types";

export function ProductRow({ product }: { product: Product }) {
  const t = useTranslations();

  return (
    <TableRow>
      <TableCell className="px-4 font-medium">{product.name}</TableCell>
      <TableCell className="px-4">{product.category.name}</TableCell>
      <TableCell className="px-4">
        <Money value={product.price} />
      </TableCell>
      <TableCell className="px-4">
        {product.stock > 0 ? t("products.stockUnits", { count: product.stock }) : t("products.outOfStock")}
      </TableCell>
      <TableCell className="flex items-start justify-end gap-1 px-4">
        <LinkButton href={`/products/${product.id}/edit`} variant="ghost" size="sm">
          {t("common.edit")}
        </LinkButton>
        <DeleteButton
          action={deleteProduct.bind(null, product.id)}
          confirmText={t("products.deleteConfirm", { name: product.name })}
        />
      </TableCell>
    </TableRow>
  );
}
