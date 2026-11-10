import Form from "next/form";
import { getTranslations } from "next-intl/server";
import { Button } from "@/shared/components/ui/button";
import { Input } from "@/shared/components/ui/input";

type Props = {
  action: string;
  placeholder: string;
  defaultValue: string;
  children?: React.ReactNode;
};

export async function SearchForm({ action, placeholder, defaultValue, children }: Props) {
  const t = await getTranslations("common");

  return (
    <Form action={action} className="mb-4 flex flex-wrap gap-2">
      <Input
        name="search"
        type="search"
        aria-label={t("searchLabel")}
        placeholder={placeholder}
        defaultValue={defaultValue}
        className="max-w-sm"
      />
      {children}
      <Button type="submit" variant="outline">
        {t("search")}
      </Button>
    </Form>
  );
}
