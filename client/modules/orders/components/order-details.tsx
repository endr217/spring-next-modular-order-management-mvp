import { useTranslations } from "next-intl";
import { DateTime } from "@/shared/components/date-time";
import { StatusBadge } from "@/modules/orders/components/status-badge";
import type { Order } from "@/modules/orders/types";

export function OrderDetails({ order }: { order: Order }) {
  const t = useTranslations("orders.fields");

  return (
    <dl className="mb-6 grid gap-4 rounded-xl border bg-card p-4 shadow-sm sm:grid-cols-3">
      <div>
        <dt className="text-sm text-muted-foreground">{t("client")}</dt>
        <dd className="font-medium">{order.client.name}</dd>
        <dd className="text-sm text-muted-foreground">{order.client.email}</dd>
      </div>
      <div>
        <dt className="text-sm text-muted-foreground">{t("date")}</dt>
        <dd>
          <DateTime value={order.createdAt} />
        </dd>
      </div>
      <div>
        <dt className="text-sm text-muted-foreground">{t("status")}</dt>
        <dd>
          <StatusBadge status={order.status} />
        </dd>
      </div>
    </dl>
  );
}
