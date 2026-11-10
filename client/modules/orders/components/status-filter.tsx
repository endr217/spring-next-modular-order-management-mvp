import Form from "next/form";
import { getTranslations } from "next-intl/server";
import { FilterSelect } from "@/shared/components/filter-select";
import { Button } from "@/shared/components/ui/button";
import { ORDER_STATUSES } from "@/modules/orders/next-statuses";

export async function StatusFilter({ defaultValue }: { defaultValue: string }) {
  const t = await getTranslations();
  const options = ORDER_STATUSES.map((status) => ({ value: status, label: t(`orders.status.${status}`) }));

  return (
    <Form action="/orders" className="mb-4 flex flex-wrap gap-2">
      <FilterSelect
        name="status"
        label={t("orders.fields.status")}
        allLabel={t("orders.allStatuses")}
        defaultValue={defaultValue}
        options={options}
      />
      <Button type="submit" variant="outline">
        {t("common.filter")}
      </Button>
    </Form>
  );
}
