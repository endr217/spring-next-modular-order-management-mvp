import { NativeSelect, NativeSelectOption } from "@/shared/components/ui/native-select";

type Props = {
  name: string;
  label: string;
  allLabel: string;
  defaultValue: string;
  options: { value: string | number; label: string }[];
};

export function FilterSelect({ name, label, allLabel, defaultValue, options }: Props) {
  return (
    <NativeSelect name={name} aria-label={label} defaultValue={defaultValue}>
      <NativeSelectOption value="">{allLabel}</NativeSelectOption>
      {options.map((option) => (
        <NativeSelectOption key={option.value} value={option.value}>
          {option.label}
        </NativeSelectOption>
      ))}
    </NativeSelect>
  );
}
