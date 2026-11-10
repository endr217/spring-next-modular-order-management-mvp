import { useTranslations } from "next-intl";
import { Money } from "@/shared/components/money";
import { Button } from "@/shared/components/ui/button";

export function OrderTotal({ total, onAdd }: { total: number; onAdd: () => void }) {
  const t = useTranslations("orders");

  return (
    <div className="flex items-center justify-between">
      <Button type="button" variant="outline" onClick={onAdd}>
        {t("addLine")}
      </Button>
      <span className="font-medium">
        {t("sum")} <Money value={total} />
      </span>
    </div>
  );
}
