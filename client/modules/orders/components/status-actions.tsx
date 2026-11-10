import { useTranslations } from "next-intl";
import { changeStatus } from "@/modules/orders/actions/change-status";
import { ActionButton } from "@/shared/components/action-button";
import { NEXT_STATUSES } from "@/modules/orders/next-statuses";
import type { Order } from "@/modules/orders/types";

export function StatusActions({ order }: { order: Order }) {
  const t = useTranslations("orders");
  const next = NEXT_STATUSES[order.status];

  if (next.length === 0) return <p className="text-sm text-muted-foreground">{t("finished")}</p>;

  return (
    <div className="flex flex-wrap gap-2" role="group" aria-label={t("changeStatus")}>
      {next.map((status) => {
        const cancel = status === "CANCELLED";
        return (
          <ActionButton
            key={status}
            action={changeStatus.bind(null, order.id, status)}
            variant={cancel ? "destructive" : "default"}
            confirmText={cancel ? t("cancelConfirm") : undefined}
          >
            {cancel ? t("cancelOrder") : t("markAs", { status: t(`status.${status}`) })}
          </ActionButton>
        );
      })}
    </div>
  );
}
