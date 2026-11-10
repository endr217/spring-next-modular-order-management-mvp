import { getTranslations } from "next-intl/server";
import { Suspense } from "react";
import { FormCard } from "@/shared/components/form-card";
import { LoadingText } from "@/shared/components/loading-text";
import { EditProduct } from "@/modules/products/components/edit-product";

export default async function EditProductPage({ params }: PageProps<"/products/[id]/edit">) {
  const t = await getTranslations("products");

  return (
    <FormCard title={t("edit")}>
      <Suspense fallback={<LoadingText />}>
        <EditProduct params={params} />
      </Suspense>
    </FormCard>
  );
}
