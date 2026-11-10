import { getTranslations } from "next-intl/server";
import { Suspense } from "react";
import { FormCard } from "@/shared/components/form-card";
import { LoadingText } from "@/shared/components/loading-text";
import { NewProduct } from "@/modules/products/components/new-product";

export default async function NewProductPage() {
  const t = await getTranslations("products");

  return (
    <FormCard title={t("new")}>
      <Suspense fallback={<LoadingText />}>
        <NewProduct />
      </Suspense>
    </FormCard>
  );
}
