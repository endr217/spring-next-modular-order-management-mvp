import { Suspense } from "react";
import { LoadingText } from "@/shared/components/loading-text";
import { OrderView } from "@/modules/orders/components/order-view";

export default function OrderPage({ params }: PageProps<"/orders/[id]">) {
  return (
    <Suspense fallback={<LoadingText />}>
      <OrderView params={params} />
    </Suspense>
  );
}
