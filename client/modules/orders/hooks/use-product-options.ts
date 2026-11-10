import { useFormatter, useTranslations } from "next-intl";
import type { Product } from "@/modules/products/types";
import { currency } from "@/i18n/config";

export function useProductOptions(products: Product[]) {
  const t = useTranslations("orders");
  const format = useFormatter();

  return products.map((product) => ({
    value: product.id,
    label: t("productOption", {
      name: product.name,
      price: format.number(product.price, { style: "currency", currency }),
      stock: product.stock,
    }),
  }));
}
